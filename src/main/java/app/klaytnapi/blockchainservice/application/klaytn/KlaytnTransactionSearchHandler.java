package app.klaytnapi.blockchainservice.application.klaytn;

import app.klaytnapi.blockchainservice.domain.klaytn.BlockchainDomainValidationMessage;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnTransaction;
import app.klaytnapi.blockchainservice.domain.klaytn.KlaytnTransactionRepository;
import app.klaytnapi.support.domain.DomainValidationException;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KlaytnTransactionSearchHandler {

    private final KlaytnTransactionRepository klaytnTransactionRepository;
    private final List<String> smartContractTxType = Arrays.asList(
            "TxTypeSmartContractDeploy",
            "TxTypeFeeDelegatedSmartContractDeploy",
            "TxTypeFeeDelegatedSmartContractDeployWithRatio",
            "TxTypeSmartContractExecution",
            "TxTypeFeeDelegatedSmartContractExecution",
            "TxTypeFeeDelegatedSmartContractExecutionWithRatio");

    public List<KlaytnTransaction> getValueTransferTransaction(long from, long to, String address) {

        List<KlaytnTransaction> result;
        if (address != null) {
            result = klaytnTransactionRepository.getValueTransferTransactionByAddress(address, from,
                    to);
        } else {
            result = klaytnTransactionRepository.getValueTransferTransaction(from, to);
        }

        if (result == null) {
            throw new DomainValidationException(
                    BlockchainDomainValidationMessage.ERROR_NO_TRANSACTION);
        }

        return result;
    }

    public List<KlaytnTransaction> getSmartContractTransaction(long from, long to, String txType) {

        List<KlaytnTransaction> result;
        if (txType != null) {
            result = klaytnTransactionRepository.getTransactionByTxType(Arrays.asList(txType), from, to);
        } else {
            result = klaytnTransactionRepository.getTransactionByTxType(smartContractTxType, from, to);
        }

        if (result.isEmpty()) {
            throw new DomainValidationException(
                    BlockchainDomainValidationMessage.ERROR_NO_TRANSACTION);
        }

        return result;
    }

}
