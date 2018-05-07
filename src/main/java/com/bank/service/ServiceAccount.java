package com.bank.service;


import com.bank.data.model.dao.AccountEntryDao;
import com.bank.data.model.entity.AccountEntryEntity;
import com.bank.vo.AccounStatisticsVO;
import com.bank.vo.AccountEntryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ServiceAccount {

    @Autowired
    AccountEntryDao accountyEntryDao;

    private AccountEntryEntity convertVoToEntity(AccountEntryVO vo) {

        AccountEntryEntity entity = new AccountEntryEntity();
        entity.setCreateDate(vo.getData());
        entity.setAccountNumber(vo.getContaContabil());
        entity.setValor(vo.getValor());
        return entity;

    }

    public AccountEntryEntity createAccountEntry(AccountEntryVO vo) throws Exception {

        AccountEntryEntity entity = convertVoToEntity(vo);

        Optional.ofNullable(vo.getContaContabil()).orElseThrow(() -> new Exception("ContaContabil invalida"));

        Optional.ofNullable(vo.getData()).orElseThrow(() -> new Exception("Data invalida"));

        Optional.ofNullable(vo.getValor()).orElseThrow(() -> new Exception("Valor invalido"));

        if (vo.getValor().compareTo(BigDecimal.ZERO) == 0) {
            throw new Exception("Valor inválido");
        }

        entity =  accountyEntryDao.create(entity);

        return entity;
    }

    private boolean validateDate(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("");

        return true;

    }

    public AccountEntryEntity getAccountEntryById(UUID id)  throws Exception{


        Optional.ofNullable(id).orElseThrow(() -> new Exception("Id informado invalido"));

        AccountEntryEntity account = accountyEntryDao.getById(id);

        if (account == null) {
            throw new Exception("Conta contábil nao localizada");
        }
        return account;
    }

    public List<AccountEntryEntity> getAccountEntryByContaContabil(Long contaContabil)  throws Exception{

        Optional.ofNullable(contaContabil).orElseThrow(() -> new Exception("Nro Conta informado invalido"));

        List<AccountEntryEntity> account = accountyEntryDao.getByContaContabil(contaContabil);

        if (account == null || account.isEmpty()) {
            throw new Exception("Conta contábil nao localizada");
        }
        return account;
    }

    public List<AccountEntryEntity> getAllAccountEntry() {

        return accountyEntryDao.getAll();
    }

    public AccounStatisticsVO getAccountStatistics() throws Exception {

        List<AccountEntryEntity> accounts = accountyEntryDao.getAll();

        if (accounts.isEmpty()) {
            throw new Exception("Nenhum registro encontrado");
        }

        return calcAccounStatistics(accounts);
    }

    public AccounStatisticsVO getAccountStatisticsByAccount(Long contaContabil) throws Exception{

        Optional.ofNullable(contaContabil).orElseThrow(() -> new Exception("Nro Conta informado invalido"));

        List<AccountEntryEntity> accounts = accountyEntryDao.getByContaContabil(contaContabil);

        if (accounts.isEmpty()) {
            throw new Exception("Nenhum registro encontrado");
        }
        return calcAccounStatistics(accounts);
    }

    private AccounStatisticsVO calcAccounStatistics(List<AccountEntryEntity> accounts) {
 /*       BigDecimal minimo = accounts.get(0).getValor();
        BigDecimal maximo = accounts.get(0).getValor();
        BigDecimal soma = BigDecimal.ZERO;
        Long qtde = 0L;


        for (AccountEntryEntity account:accounts) {
            qtde++;
            soma = soma.add(account.getValor());
            maximo = maximo.max(account.getValor());
            minimo = minimo.min(account.getValor());
        }
*/
        Optional<BigDecimal> maximo = accounts.stream()
                .map(a -> a.getValor())
                .max(Comparator.naturalOrder());

        Optional<BigDecimal> minimo =  accounts.stream()
                .map(a -> a.getValor())
                .max(Comparator.reverseOrder());

        BigDecimal soma = accounts.stream()
                .map(x -> x.getValor())
                .reduce(BigDecimal.ZERO, (a,b) -> a.add(b));

        Optional<BigDecimal> soma2 = Optional.of(soma);

        Long qtde = accounts.stream()
                .map(a -> a.getValor()).count();


        Optional<BigDecimal> media = Optional.of(soma.divide(new BigDecimal(qtde)));

        return new AccounStatisticsVO(soma2, minimo, maximo, media , qtde);
    }
}
