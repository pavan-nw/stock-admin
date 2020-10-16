package com.stock.admin.controller;

import com.stock.admin.exception.StockAdminApplicationException;
import com.stock.admin.model.entity.Shop;
import com.stock.admin.model.request.ShopRequest;
import com.stock.admin.model.response.Response;
import com.stock.admin.service.SequenceGeneratorService;
import com.stock.admin.service.ShopService;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Shop controller.
 */
@RestController
@RequestMapping("/shops")
public class ShopController {
    private final ShopService shopService;
    private final SequenceGeneratorService sequenceGeneratorService;


    /**
     * Instantiates a new Shop controller.
     *
     * @param shopService              the shop service
     * @param sequenceGeneratorService the sequence generator service
     */
    @Autowired
    public ShopController(ShopService shopService,SequenceGeneratorService sequenceGeneratorService) {
        this.shopService = shopService;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    /**
     * Gets all shops.
     *
     * @return the all shops
     */
    @GetMapping
    public Response getAllShops() {
        return Response.buildResponse(Shop.type, shopService.getAll(), true);

    }

    /**
     * Gets shop by id.
     *
     * @param shopCode the shop code
     * @return the shop by id
     */
    @GetMapping(value = "/{shopCode}")
    public Response getShopById(@PathVariable("shopCode") String shopCode) {
        Optional<Shop> shop = shopService.getByShopCode(shopCode);
        return shop.map(s -> Response.buildResponse(Shop.type, s, true))
                .orElseThrow(() -> new StockAdminApplicationException("Shop does not exists", HttpStatus.NOT_FOUND));

    }

    /**
     * Add shop shop.
     *
     * @param shopRequest the new shop
     * @return the shop
     */
    @PostMapping
    @ResponseBody
    public Response addShop(@Valid @RequestBody ShopRequest shopRequest) {
        shopRequest.getShop().setShopCode(sequenceGeneratorService.generateSequence(Shop.SEQUENCE_NAME));
        Shop addedShop = shopService.create(shopRequest.getShop());
        return Response.buildResponse(Shop.type, addedShop, true);
    }


    /**
     * Modify by shop code response.
     *
     * @param shopCode    the shop code
     * @param shopRequest the shop request
     * @return the response
     */
    @PutMapping(value = "/{shopCode}")
    @ResponseBody
    public Response modifyByShopCode(@PathVariable("shopCode") String shopCode, @Valid @RequestBody ShopRequest shopRequest) {
        Shop updatedShop = shopService.updateByShopCode(shopCode, shopRequest.getShop());
        return Response.buildResponse(Shop.type, updatedShop, true);
    }


    /**
     * Delete by shop code response.
     *
     * @param shopCode the shop code
     * @return the response
     */
    @DeleteMapping(value = "/{shopCode}")
    public Response deleteByShopCode(@PathVariable("shopCode") String shopCode) {
        return Response.buildResponse(Shop.type, shopService.delete(shopCode), true);
    }
}
