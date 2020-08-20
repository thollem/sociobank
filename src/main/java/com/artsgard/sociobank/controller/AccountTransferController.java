package com.artsgard.sociobank.controller;

import com.artsgard.sociobank.dto.AccountTransferDTO;
import com.artsgard.sociobank.service.AccountTransferService;
import com.artsgard.sociobank.service.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class AccountTransferController {

    org.slf4j.Logger logger = LoggerFactory.getLogger(AccountTransferController.class);

    @Autowired
    private AccountTransferService transferService;

    @Autowired
    private MapperService mapperService;

    /**
     *
     * @return
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(transferService.findAllAccountTransfers(), HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<?> findTransferById(@PathVariable Long id) {
        List<AccountTransferDTO> transfers = transferService.findAccountTransfersByAccountId(id);
        return new ResponseEntity<>(transfers, HttpStatus.OK);
    }

    /**
     *
     * @param transferDTO
     * @return
     */
    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> saveTransfer(@Valid @RequestBody AccountTransferDTO transferDTO) {
        return new ResponseEntity<>(transferService.updateAccountTransfer(transferDTO), HttpStatus.CREATED);
    }
}
