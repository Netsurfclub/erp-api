package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.warehouse.constants.EndpointConstants.PRODUCT_PHOTOS_ENDPOINT
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = [PRODUCT_PHOTOS_ENDPOINT])
class ProductPhotoController
