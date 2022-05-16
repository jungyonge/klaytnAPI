package app.klaytnapi.api.klaytn;

import app.klaytnapi.api.klaytn.response.BlockDto;
import app.klaytnapi.blockchainservice.application.klaytn.KlaytnBlockSearchHandler;
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
@RequestMapping("/api/v1/klaytn/block")
public class KlaytnBlockSearchController {

    private final KlaytnBlockSearchHandler klaytnBlockSearchHandler;
    private final ModelMapper modelMapper;

    public KlaytnBlockSearchController(
            KlaytnBlockSearchHandler klaytnBlockSearchHandler,
            ModelMapper modelMapper) {
        this.klaytnBlockSearchHandler = klaytnBlockSearchHandler;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<BlockDto> getBlock(@RequestParam long from, @RequestParam long to,
            @RequestParam(required = false) @Nullable Double totalKlayValues,
            @RequestParam(required = false) @Nullable Integer transactionCount) throws DomainValidationException {

        var result = klaytnBlockSearchHandler.getBlock(from - 1, to + 1, totalKlayValues, transactionCount);
        Type listType = new TypeToken<List<BlockDto>>() {}.getType();
        List<BlockDto> list = modelMapper.map(result, listType);
        return list;
    }

}
