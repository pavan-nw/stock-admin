package com.stock.admin.controller;

import com.stock.admin.exception.StockAdminApplicationException;
import com.stock.admin.model.entity.Shop;
import com.stock.admin.model.request.ShopRequest;
import com.stock.admin.model.response.ErrorResponse;
import com.stock.admin.model.response.Response;
import com.stock.admin.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * The type Shop controller.
 */
@RestController
@RequestMapping("/shops")
public class ShopController {
    @Autowired
    private ShopService shopService;

    /**
     * Gets all shops.
     *
     * @return the all shops
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public Response getAllShops() {
        return Response.buildResponse(Shop.type, shopService.getAll(), true);

    }

    /**
     * Gets shop by id.
     *
     * @param shopCode the shop code
     * @return the shop by id
     */
    @GetMapping(value = "/{shopCode}", produces = "application/json; charset=UTF-8")
    public Response getShopById(@PathVariable("shopCode") String shopCode) {
        Optional<Shop> shop = shopService.getByShopCode(shopCode);
        return shop.map(s -> Response.buildResponse(Shop.type, s, true))
                .orElseThrow(() -> new StockAdminApplicationException(new
                        ErrorResponse(404, "Shop does not exists"), HttpStatus.NOT_FOUND));

    }

    /**
     * Add shop shop.
     *
     * @param shopRequest the new shop
     * @return the shop
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Response addShop(@RequestBody ShopRequest shopRequest) {
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
    @RequestMapping(value = "/{shopCode}", method = RequestMethod.PUT)
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
    @RequestMapping(value = "/{shopCode}", method = RequestMethod.DELETE)
    public Response deleteByShopCode(@PathVariable("shopCode") String shopCode) {
        return Response.buildResponse(Shop.type, shopService.delete(shopCode), true);
    }
}
