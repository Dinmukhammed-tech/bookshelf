package com.bookshelf.controller;

import com.bookshelf.dto.ShelfRequest;
import com.bookshelf.dto.ShelfResponse;
import com.bookshelf.service.ShelfService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shelf")
@AllArgsConstructor
public class ShelfController {

    private final ShelfService shelfService;

    @PutMapping
    public ShelfResponse addtoShelf(@Valid @RequestBody ShelfRequest request, Authentication authentication){
        return shelfService.addToShelf(request,authentication.getName());
    }

    @GetMapping
    public List<ShelfResponse> getMyShelf(Authentication authentication){
        return shelfService.getMyShelf(authentication.getName());
    }
}
