package app.klaytnapi.api.klaytn;

import app.klaytnapi.api.klaytn.response.TransactionDto;
import app.klaytnapi.blockchainservice.application.klaytn.KlaytnTransactionSearchHandler;
import app.klaytnapi.support.domain.DomainValidationException;
import com.mongodb.lang.Nullable;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/klaytn/transaction")
public class KlaytnTransactionSearchController {

    private final KlaytnTransactionSearchHandler klaytnTransactionSearchHandler;
    private final ModelMapper modelMapper;

    public KlaytnTransactionSearchController(
            KlaytnTransactionSearchHandler klaytnTransactionSearchHandler,
            ModelMapper modelMapper) {
        this.klaytnTransactionSearchHandler = klaytnTransactionSearchHandler;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/valuetransfer")
    public List<TransactionDto> getValueTransferTransaction(@RequestParam long from, @RequestParam long to,
            @RequestParam(required = false) @Nullable String address) throws DomainValidationException {

        var result = klaytnTransactionSearchHandler.getValueTransferTransaction(from, to, address);
        Type listType = new TypeToken<List<TransactionDto>>() {}.getType();
        List<TransactionDto> list = modelMapper.map(result, listType);
        return list;
    }

    @GetMapping("/smartcontract")
    public List<TransactionDto> getSmartContractTransaction(@RequestParam long from, @RequestParam long to,
            @RequestParam(required = false) @Nullable String txType) throws DomainValidationException {

        var result = klaytnTransactionSearchHandler.getSmartContractTransaction(from, to, txType);
        Type listType = new TypeToken<List<TransactionDto>>() {}.getType();
        List<TransactionDto> list = modelMapper.map(result, listType);
        return list;
    }

}
