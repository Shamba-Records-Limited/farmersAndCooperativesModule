package com.shabarecords.farmersmodule.controller.titles;

import com.shabarecords.farmersmodule.models.title.Title;
import com.shabarecords.farmersmodule.services.title.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/26/21, Wed
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/titles")
public class TitleController {

    private final TitleService titleService;

    @PostMapping
    public ResponseEntity<Title> addTitle(Title title) {
        return new ResponseEntity<>(titleService.addTitle(title), HttpStatus.OK);
    }


    @GetMapping("/{titleId}")
    public ResponseEntity<Title> getTitle(@PathVariable Integer titleId) {

        return new ResponseEntity<>(titleService.getTitle(titleId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Title>> addTitles(@RequestParam() int page, @RequestParam() int size) {

        return new ResponseEntity<>(titleService.getTitles(PageRequest.of(page, size)), HttpStatus.OK);
    }
}
