package com.example.nobsv2.nobsv2.transaction;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nobsv2.nobsv2.Command;

@Service
@Transactional
public class TransferService implements Command<TransferDTO, String> {
    private final BankAccountRepository bankAccountRepository;

    public TransferService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public ResponseEntity<String> execute(TransferDTO input) {
        Optional<BankAccount> optionalFrom = this.bankAccountRepository.findById(input.getFromUser());
        Optional<BankAccount> optionalTo = this.bankAccountRepository.findById(input.getToUser());

        if (optionalFrom.isEmpty() || optionalTo.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        BankAccount from = optionalFrom.get();
        BankAccount to = optionalTo.get();

        add(to, input.getAmount());
        deduct(from, input.getAmount());

        return ResponseEntity.ok("Success");
    }

    private void deduct(BankAccount bankAccount, double amount) {
        if (bankAccount.getBalance() < amount) {
            throw new RuntimeException("not enough money");
        }
        bankAccount.setBalance(bankAccount.getBalance() - amount);
    }

    private void add(BankAccount bankAccount, double amount) {
        bankAccount.setBalance(bankAccount.getBalance() + amount);
    }

}
