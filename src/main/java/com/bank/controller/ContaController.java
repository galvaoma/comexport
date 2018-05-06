package com.bank.controller;

import com.bank.data.model.entity.AccountEntryEntity;
import com.bank.service.ServiceAccount;
import com.bank.vo.AccounStatisticsVO;
import com.bank.vo.AccountEntryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Api(basePath = "swagger-demo/conta", value = "conta",  produces = "application/json")
@RestController
@RequestMapping(value = "/conta")
public class ContaController {

    @Autowired
    ServiceAccount serviceAccount;


    @ApiOperation(value = "Adiciona lançamento contabil")
    @RequestMapping(value = "/lancamentos-contabeis",  method = PUT)
    @ResponseBody
    public ResponseEntity<?> criaLancamentoContabil(@RequestBody AccountEntryVO vo) {

        AccountEntryEntity account;

        try {
            account = serviceAccount.createAccountEntry(vo);
        } catch(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }

        String retorno =  "{\n \"id\":" + account.getAccountId().toString() + " \n}";

        return new ResponseEntity(retorno, HttpStatus.OK);

    }

    @ApiOperation(value = "Busca lancamento contabil por id")
    @RequestMapping(value = "/lancamentos-contabeis/{id}",  method = GET)
    @ResponseBody
    public ResponseEntity<?> buscaLancamentoContabil(@PathVariable Long id) {

        AccountEntryEntity entity;

        try {
            entity = serviceAccount.getAccountEntryById(id);
        } catch(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(entity, HttpStatus.OK);
    }

    @ApiOperation(value = "Busca lancamento contabil por conta contabil")
    @RequestMapping(value = "/lancamentos-contabeis",  method = GET)
    @ResponseBody
    public ResponseEntity<?>  buscaLancamentoContabilPorConta(@RequestParam("contaContabil") Long id) {

        List<AccountEntryEntity> entities = null;

        try {
               entities = serviceAccount.getAccountEntryByContaContabil(id);
        } catch(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(entities, HttpStatus.OK);

    }

    @ApiOperation(value = "Busca estatistica do lancamento contabil")
    @RequestMapping(value = "/lancamentos-contabeis/_stat",  method = GET)
    @ResponseBody
    public ResponseEntity<?> buscaLancamentoContabilStats() {

        AccounStatisticsVO entity;

        try {
            entity = serviceAccount.getAccountStatistics();
        } catch(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(entity, HttpStatus.OK);
    }

    @ApiOperation(value = "Busca lancamento contabil por conta contabil")
    @RequestMapping(value = "/lancamentos-contabeis/_stats",  method = GET)
    @ResponseBody
    public ResponseEntity<?>  buscaLancamentoContabilStatsPorConta(@RequestParam("contaContabil") Long id) {

        AccounStatisticsVO entity;

        try {
            entity = serviceAccount.getAccountStatisticsByAccount(id);
        } catch(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(entity, HttpStatus.OK);

    }
}
