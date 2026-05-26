package com.example.bfhl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bfhl")
public class BfhlController {

    @Autowired
    private BfhlService bfhlService;

    @PostMapping
    public ResponseEntity<ResponseDTO> processData(@RequestBody RequestDTO request) {
        try {
            ResponseDTO response = bfhlService.processData(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseDTO error = new ResponseDTO();
            error.setIs_success(false);
            return ResponseEntity.status(500).body(error);
        }
    }
}
