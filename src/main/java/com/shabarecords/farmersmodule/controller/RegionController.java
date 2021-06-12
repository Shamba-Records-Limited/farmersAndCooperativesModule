package com.shabarecords.farmersmodule.controller;

import com.shabarecords.farmersmodule.models.Region;
import com.shabarecords.farmersmodule.services.RegionService;
import com.shabarecords.farmersmodule.utils.APIResponse;
import com.shabarecords.farmersmodule.utils.databind.RegionRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/26/21, Wed
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/regions")
@Api(tags = {"Regions"})
public class RegionController {

    private final RegionService regionService;


    @PostMapping

    @ApiOperation(value = "Add Region")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = " Bad request ", response = APIResponse.class),
            @ApiResponse(code = 500, message = " Server error", response = APIResponse.class),
            @ApiResponse(code = 401, message = " Access Denied, Authorization token is required", response = APIResponse.class),
            @ApiResponse(code = 403, message = " Unauthorized, Authorization token is required", response = APIResponse.class)
    })
    public ResponseEntity<APIResponse> addRegion(@Valid @RequestBody RegionRequest regionRequest) {
        return regionService.addRegion(regionRequest);
    }

    @GetMapping("/{code}")
    @ApiOperation(value = "Find region given region Code")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = " Bad request ", response = APIResponse.class),
            @ApiResponse(code = 500, message = " Server error", response = APIResponse.class),
            @ApiResponse(code = 401, message = " Access Denied, Authorization token is required", response = APIResponse.class),
            @ApiResponse(code = 403, message = " Unauthorized, Authorization token is required", response = APIResponse.class)
    })
    public ResponseEntity<Region> getRegion(@PathVariable String code) {
        return regionService.getRegion(code);
    }

    @GetMapping
    @ApiOperation(value = "All Regions Covered by Organisation")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = " Bad request ", response = APIResponse.class),
            @ApiResponse(code = 500, message = " Server error", response = APIResponse.class),
            @ApiResponse(code = 401, message = " Access Denied, Authorization token is required", response = APIResponse.class),
            @ApiResponse(code = 403, message = " Unauthorized, Authorization token is required", response = APIResponse.class)
    })
    public ResponseEntity<List<Region>> getRegions() {
        return regionService.getRegions();
    }

}
