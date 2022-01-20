package com.example.eCommerce.prac1.order.service;

import com.example.eCommerce.prac1.utility.log.AppLogger;
import com.example.eCommerce.prac1.utility.service.BaseService;
import com.example.eCommerce.prac1.order.domain.Order;
import com.example.eCommerce.prac1.order.mapper.OrderMapper;
import com.example.eCommerce.prac1.order.model.OrderDTO;
import com.example.eCommerce.prac1.order.model.OrderDetailsDTO;
import com.example.eCommerce.prac1.order.model.OrderHistoryReqDTO;
import com.example.eCommerce.prac1.order.repository.OrderRepository;
import com.example.eCommerce.prac1.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService extends BaseService<OrderDTO, Order, Long> {
    private OrderRepository repository;
    private OrderMapper mapper;
    @Autowired
    private ProductService productService;

    public OrderService(OrderRepository repository, OrderMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * After saving Order, stock of product defined in OrderDetails get deducted..
     *
     * @param model OrderDTO
     * @return OrderDTO
     * @throws Exception
     */
    @Override
    public OrderDTO saveEntity(OrderDTO model) throws Exception {
        OrderDTO savedOrder = super.saveEntity(model);
        addOrDeductStock(savedOrder.getOrderDetails(), false);
        return savedOrder;
    }

    /**
     * Before deleting Order, stock of product defined in OrderDetails get added..
     *
     * @param model OrderDTO
     * @throws Exception
     */
    @Override
    public void deleteEntity(OrderDTO model) throws Exception {
        addOrDeductStock(model.getOrderDetails(), true);
        super.deleteEntity(model);
    }

    /**
     * manipulate stock of differences and then update order
     *
     * @param model OrderDTO
     * @return OrderDTO
     * @throws Exception
     */
    @Override
    public OrderDTO updateEntity(OrderDTO model) throws Exception {
        OrderDTO dbModel = getEntityById(model.getId());
        updateStock(model, dbModel);
        return super.updateEntity(model);
    }

    /**
     * there will be three cases in update
     * 1. new product added in OrderDetails(deduct stock)
     * 2. previously added product removed(add stock)
     * 3. for same product only quantity is changed(deduct if qty increases & add if qty decreases)
     *
     * @param newDTO OrderDTO
     * @param dbDTO  OrderDTO
     * @throws Exception
     */
    private void updateStock(OrderDTO newDTO, OrderDTO dbDTO) throws Exception {
        try {
            if (null != newDTO.getOrderDetails() && null != dbDTO.getOrderDetails()) {
                //--New Entry of details(case of save)
                List<OrderDetailsDTO> newDetails = newDTO.getOrderDetails()
                        .stream()
                        .filter(data -> null == data.getId()).collect(Collectors.toList());
                addOrDeductStock(newDetails, false);

                //--Deleted Entry in New(case of delete)
                List<OrderDetailsDTO> deletedList = new ArrayList<>();
                for (OrderDetailsDTO dbDetails : dbDTO.getOrderDetails()) {
                    OrderDetailsDTO filteredDTO = newDTO.getOrderDetails()
                            .stream()
                            .filter(data -> null != data.getId() && (data.getId().equals(dbDetails.getId())))
                            .findFirst().orElse(null);
                    if (null == filteredDTO)
                        deletedList.add(dbDetails);
                }
                addOrDeductStock(deletedList, true);

                //--Same Entry in new and DB
                for (OrderDetailsDTO detailsDTO : newDTO.getOrderDetails()) {
                    OrderDetailsDTO dbDetailsDTO = dbDTO.getOrderDetails()
                            .stream().filter(data -> data.getId().equals(detailsDTO.getId()))
                            .findFirst().orElse(null);
                    if (null != dbDetailsDTO) {
                        Double dbQty = dbDetailsDTO.getQty();
                        Double newQty = detailsDTO.getQty();
                        double netQty = newQty - dbQty;
                        if (netQty > 0) //case of save
                            productService.deductProductStock(detailsDTO.getProduct(), netQty);
                        if (netQty < 0) //case of delete
                            productService.addProductStock(detailsDTO.getProduct(), (netQty * -1));
                    }
                }
            }
        } catch (Exception ex) {
            AppLogger.logger.error(getModuleName() + " [updateStock()] " + ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * @param orderDetails List<OrderDetailsDTO>
     * @param isAdd        boolean (set true for adding stock, set false for deducting stock)
     * @throws Exception
     */
    private void addOrDeductStock(List<OrderDetailsDTO> orderDetails, boolean isAdd) throws Exception {
        try {
            if (null != orderDetails && 0 < orderDetails.size()) {
                for (OrderDetailsDTO detailsDTO : orderDetails) {
                    if (isAdd)
                        productService.addProductStock(detailsDTO.getProduct(), detailsDTO.getQty());
                    else
                        productService.deductProductStock(detailsDTO.getProduct(), detailsDTO.getQty());
                }
            }
        } catch (Exception ex) {
            AppLogger.logger.error(getModuleName() + " [addOrDeductStock()] " + ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * it returns list of orders in dateWise sorted manner placed by requestedUser
     * if startDate given for filtering, provide data between given startDate and currentDate
     *
     * @param reqDTO OrderHistoryReqDTO
     * @return List<OrderDTO>
     */
    public List<OrderDTO> getFilteredOrder(OrderHistoryReqDTO reqDTO) {
        List<Order> resultList = new ArrayList<>();
        try {
            if (null != reqDTO.getLoggedInUser()) {
                if (null != reqDTO.getStartDate()) {
                    reqDTO.setEndDate(null != reqDTO.getEndDate() ? LocalDateTime.now() : reqDTO.getEndDate());
                    resultList = repository.findAllByCustomer_IdAndDateBetweenOrderByDateDesc(reqDTO.getLoggedInUser().getId(), reqDTO.getStartDate(), reqDTO.getEndDate());
                } else
                    resultList = repository.findAllByCustomer_IdOrderByDateDesc(reqDTO.getLoggedInUser().getId());
            }
            return (null != resultList && 0 < resultList.size())
                    ? resultList.stream()
                    .map(data -> mapper.domainToDTO(data))
                    .collect(Collectors.toList()) : new ArrayList<>();
        } catch (Exception ex) {
            AppLogger.logger.error(getModuleName() + " [getFilteredOrder()] " + ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public String getModuleName() {
        return "[ Order Service ]";
    }
}
