package com.example.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.NotFoundException;
import com.example.entity.Product;
import com.example.entity.Productinstore;
import com.example.entity.Store;
import com.example.repository.ProductRepository;
import com.example.repository.ProductinstoreRepository;
import com.example.repository.StoreRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductinstoreRepository productinstoreRepository;

    @Autowired
    private StoreRepository storeRepository;

    @RequestMapping(value = "/api/scanProduct/{productId}/{storeId}/{quantity}", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.entity.Productinstore.class)
    public Productinstore scanProduct(@PathVariable("productId") Long productId, @PathVariable("storeId") Long storeId, @PathVariable("quantity") int quantity) {
        Product product = productRepository.findOne(productId);
        if (product == null) {
            throw new NotFoundException(productId.toString());
        }

        Store store = storeRepository.findOne(storeId);
        if (store == null) {
            throw new NotFoundException(storeId.toString());
        }

        Productinstore newProductinstore = new Productinstore();
        newProductinstore.setProduct(product);
        newProductinstore.setStore(store);
        newProductinstore.setQuantity(quantity);

        //newLocation = locationRepository.save(newLocation);

        product.getProductinstores().add(newProductinstore);
        productRepository.save(product);

        return newProductinstore;
    }


    /*
    @RequestMapping(value = "/api/placeWidget/{workspaceId}/{widgetId}/{rowNumber}/{columnNumber}", method = RequestMethod.GET, produces = "application/json")
    @JsonView(com.example.entity.Location.class)
    public Location placeWidget(@PathVariable("workspaceId") Long workspaceId, @PathVariable("widgetId") Long widgetId, @PathVariable("rowNumber") int rowNumber, @PathVariable("columnNumber") int columnNumber) {
        Workspace workspace = workspaceRepository.findOne(workspaceId);
        if (workspace == null) {
            throw new NotFoundException(workspaceId.toString());
        }

        Widget widget = widgetRepository.findOne(widgetId);
        if (widget == null) {
            throw new NotFoundException(widgetId.toString());
        }

        Location newLocation = new Location();
        newLocation.setWorkspace(workspace);
        newLocation.setWidget(widget);
        newLocation.setColumnNumber(columnNumber);
        newLocation.setRowNumber(rowNumber);
        newLocation = locationRepository.save(newLocation);

        workspace.getWidgetLocations().add(newLocation);
        workspaceRepository.save(workspace);

        return newLocation;
    }
*/
}
