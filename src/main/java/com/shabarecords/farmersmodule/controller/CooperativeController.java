package com.shabarecords.farmersmodule.controller;


import com.shabarecords.farmersmodule.services.CooperativeService;
import com.shabarecords.farmersmodule.utils.APIResponse;
import com.shabarecords.farmersmodule.utils.databind.*;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/22/21, Sat
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/cooperatives")
@Api(tags = {"Cooperatives"})
public class CooperativeController {

    private final CooperativeService cooperativeService;


    @PostMapping

    @ApiOperation(value = "Add Cooperative")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = " Bad request ", response = APIResponse.class),
            @ApiResponse(code = 500, message = " Server error", response = APIResponse.class),
            @ApiResponse(code = 401, message = " Access Denied, Authorization token is required", response = APIResponse.class),
            @ApiResponse(code = 403, message = " Unauthorized, Authorization token is required", response = APIResponse.class)
    })
    public ResponseEntity<APIResponse> addCooperative(@Valid @RequestBody AddCooperativeRequest request) {

        return cooperativeService.addCooperative(request);
    }

    @PutMapping("/{cooperativeId}")

    @ApiOperation(value = "Update Cooperative")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = " Bad request ", response = APIResponse.class),
            @ApiResponse(code = 500, message = " Server error", response = APIResponse.class),
            @ApiResponse(code = 401, message = " Access Denied, Authorization token is required", response = APIResponse.class),
            @ApiResponse(code = 403, message = " Unauthorized, Authorization token is required", response = APIResponse.class)
    })
    public ResponseEntity<APIResponse> updateCooperative(@PathVariable Long cooperativeId, @Valid @RequestBody UpdateCooperativeRequest updateCooperativeRequest) {

        return cooperativeService.updateCooperative(cooperativeId, updateCooperativeRequest);
    }

    @GetMapping

    @ApiOperation(value = "All Cooperatives")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = " Bad request ", response = APIResponse.class),
            @ApiResponse(code = 500, message = " Server error", response = APIResponse.class),
            @ApiResponse(code = 401, message = " Access Denied, Authorization token is required", response = APIResponse.class),
            @ApiResponse(code = 403, message = " Unauthorized, Authorization token is required", response = APIResponse.class)
    })
    public ResponseEntity<List<Cooperative>> getAllCoperatives() {

        return cooperativeService.getAllCoperatives();
    }

    @GetMapping("/{code}")

    @ApiOperation(value = "Find Cooperative given Cooperative Code")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = " Bad request ", response = APIResponse.class),
            @ApiResponse(code = 500, message = " Server error", response = APIResponse.class),
            @ApiResponse(code = 401, message = " Access Denied, Authorization token is required", response = APIResponse.class),
            @ApiResponse(code = 403, message = " Unauthorized, Authorization token is required", response = APIResponse.class)
    })
    public ResponseEntity<Cooperative> getCooperative(@PathVariable String code) {
        return cooperativeService.getCooperative(code);
    }


    @PostMapping("/{cooperativeId}/farmers")

    @ApiOperation(value = "Add Farmer to Cooperative")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = " Bad request ", response = APIResponse.class),
            @ApiResponse(code = 500, message = " Server error", response = APIResponse.class),
            @ApiResponse(code = 401, message = " Access Denied, Authorization token is required", response = APIResponse.class),
            @ApiResponse(code = 403, message = " Unauthorized, Authorization token is required", response = APIResponse.class)
    })
    public ResponseEntity<APIResponse> addCoopFarmer(@PathVariable Long cooperativeId,
                                                     @Valid @RequestBody AddCooperativeFarmer addCooperativeFarmer
    ) {


        return cooperativeService.addCoopFarmer(cooperativeId, addCooperativeFarmer);
    }


    @GetMapping("/{cooperativeId}/farmers")

    @ApiOperation(value = "Get Cooperative Farmers given Cooperative Id ")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = " Bad request ", response = APIResponse.class),
            @ApiResponse(code = 500, message = " Server error", response = APIResponse.class),
            @ApiResponse(code = 401, message = " Access Denied, Authorization token is required", response = APIResponse.class),
            @ApiResponse(code = 403, message = " Unauthorized, Authorization token is required", response = APIResponse.class)
    })
    public ResponseEntity<List<CoopFarmers>> getCoopFarmers(@PathVariable Long cooperativeId) {

        return cooperativeService.getCoopFarmers(cooperativeId);
    }

    @PostMapping("/{cooperativeId}/farm")

    @ApiOperation(value = "Add Cooperative's Farm")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = " Bad request ", response = APIResponse.class),
            @ApiResponse(code = 500, message = " Server error", response = APIResponse.class),
            @ApiResponse(code = 401, message = " Access Denied, Authorization token is required", response = APIResponse.class),
            @ApiResponse(code = 403, message = " Unauthorized, Authorization token is required", response = APIResponse.class)
    })
    public ResponseEntity<APIResponse> addFarm(
            @PathVariable Long cooperativeId,
            @Valid @RequestBody FarmRequest farmRequest
    ) {


        return cooperativeService.addFarm(cooperativeId, farmRequest);
    }


    @GetMapping("/{coopCode}/farm")

    @ApiOperation(value = "Get Cooperative's Available Farms")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = " Bad request ", response = APIResponse.class),
            @ApiResponse(code = 500, message = " Server error", response = APIResponse.class),
            @ApiResponse(code = 401, message = " Access Denied, Authorization token is required", response = APIResponse.class),
            @ApiResponse(code = 403, message = " Unauthorized, Authorization token is required", response = APIResponse.class)
    })
    public ResponseEntity<List<FarmData>> getCooperativeFarms(@ApiParam(value = "cooperativeCode") @PathVariable String coopCode) {

        return cooperativeService.getCooperativeFarms(coopCode);
    }


}
