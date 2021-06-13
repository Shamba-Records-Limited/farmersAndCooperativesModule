package com.shabarecords.farmersmodule.controller;

import com.shabarecords.farmersmodule.services.FarmerService;
import com.shabarecords.farmersmodule.utils.APIResponse;
import com.shabarecords.farmersmodule.utils.databind.*;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * @author : Odinga David
 * @since : 5/20/21, Thu
 */


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/farmers")
@Api(tags = {"Farmers"})
public class FarmerController {

    private final FarmerService farmerService;

    @PostMapping

    @ApiOperation(value = "Add Farmer")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = " Bad request ", response = APIResponse.class),
            @ApiResponse(code = 500, message = " Server error", response = APIResponse.class),
            @ApiResponse(code = 401, message = " Access Denied, Authorization token is required", response = APIResponse.class),
            @ApiResponse(code = 403, message = " Unauthorized, Authorization token is required", response = APIResponse.class)
    })
    public ResponseEntity<APIResponse> addFarmer(@Valid @RequestBody AddFarmerRequest request) {

        return farmerService.addFarmer(request);
    }

    @PutMapping("/{farmerId}")

    @ApiOperation(value = "Update Farmer")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = " Bad request ", response = APIResponse.class),
            @ApiResponse(code = 500, message = " Server error", response = APIResponse.class),
            @ApiResponse(code = 401, message = " Access Denied, Authorization token is required", response = APIResponse.class),
            @ApiResponse(code = 403, message = " Unauthorized, Authorization token is required", response = APIResponse.class)
    })
    public ResponseEntity<APIResponse> updateFarmer(@PathVariable("farmerId") Long growerCode,
                                                    @Valid @RequestBody UpdateFarmerRequest updateRequest) {
        return farmerService.updateFarmer(growerCode, updateRequest);
    }


    @GetMapping("/{growerCode}")

    @ApiOperation(value = "Find Farmer given growerCode")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = " Bad request ", response = APIResponse.class),
            @ApiResponse(code = 500, message = " Server error", response = APIResponse.class),
            @ApiResponse(code = 401, message = " Access Denied, Authorization token is required", response = APIResponse.class),
            @ApiResponse(code = 403, message = " Unauthorized, Authorization token is required", response = APIResponse.class)
    })
    public ResponseEntity<Farmer> getFarmer(@PathVariable String growerCode) {
        return farmerService.getFarmer(growerCode);
    }


    @GetMapping

    @ApiOperation(value = "Get Available Farmers")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = " Bad request ", response = APIResponse.class),
            @ApiResponse(code = 500, message = " Server error", response = APIResponse.class),
            @ApiResponse(code = 401, message = " Access Denied, Authorization token is required", response = APIResponse.class),
            @ApiResponse(code = 403, message = " Unauthorized, Authorization token is required", response = APIResponse.class)
    })
    public ResponseEntity<Page<Farmer>> getFarmers(@ApiParam(value = "size", defaultValue = "500")
                                                   @RequestParam(value = "size", required = false) int size,
                                                   @RequestParam(value = "page", required = false, defaultValue = "0") int page) {

        Pageable pageable = PageRequest.of(Objects.isNull(page) ? 0 : page, Objects.isNull(size) ? 500 : size);
        return farmerService.getFarmers(pageable);
    }


    @PostMapping("/{farmerId}/farm")

    @ApiOperation(value = "Add Farmer's Farm")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = " Bad request ", response = APIResponse.class),
            @ApiResponse(code = 500, message = " Server error", response = APIResponse.class),
            @ApiResponse(code = 401, message = " Access Denied, Authorization token is required", response = APIResponse.class),
            @ApiResponse(code = 403, message = " Unauthorized, Authorization token is required", response = APIResponse.class)
    })
    public ResponseEntity<APIResponse> addFarm(
            @PathVariable Long farmerId,
            @Valid @RequestBody FarmRequest farmRequest
    ) {


        return farmerService.addFarm(farmerId, farmRequest);
    }


    @GetMapping("/{growerCode}/farm")
    @ApiOperation(value = "Get Farmer's Available Farms")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = " Bad request ", response = APIResponse.class),
            @ApiResponse(code = 500, message = " Server error", response = APIResponse.class),
            @ApiResponse(code = 401, message = " Access Denied, Authorization token is required", response = APIResponse.class),
            @ApiResponse(code = 403, message = " Unauthorized, Authorization token is required", response = APIResponse.class)
    })
    public ResponseEntity<List<FarmData>> getGrowerFarms(@PathVariable String growerCode) {

        return farmerService.getGrowerFarms(growerCode);
    }


}
