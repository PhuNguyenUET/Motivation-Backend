package motivation.com.motivation.Configuration;

import motivation.com.motivation.DTO.DisplayQuoteDTO;
import motivation.com.motivation.Model.Quote;
import motivation.com.motivation.Model.UserQuote;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapperBean() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<Quote, DisplayQuoteDTO> quoteDTOConverter = new Converter<Quote, DisplayQuoteDTO>() {
            @Override
            public DisplayQuoteDTO convert(MappingContext<Quote, DisplayQuoteDTO> context) {
                Quote quote = context.getSource();
                DisplayQuoteDTO displayQuoteDTO = context.getDestination();
                displayQuoteDTO.setQuote(quote.getQuote());
                displayQuoteDTO.setAuthor(quote.getAuthor());
                displayQuoteDTO.setUserCreated(false);
                return displayQuoteDTO;
            }
        };
        modelMapper.addConverter(quoteDTOConverter);
        Converter<UserQuote, DisplayQuoteDTO> userQuoteFavouriteQuoteDTOConverter = new Converter<UserQuote, DisplayQuoteDTO>() {
            @Override
            public DisplayQuoteDTO convert(MappingContext<UserQuote, DisplayQuoteDTO> context) {
                UserQuote quote = context.getSource();
                DisplayQuoteDTO displayQuoteDTO = context.getDestination();
                displayQuoteDTO.setQuote(quote.getQuote());
                displayQuoteDTO.setAuthor(quote.getAuthor());
                displayQuoteDTO.setUserCreated(true);
                return displayQuoteDTO;
            }
        };
        modelMapper.addConverter(userQuoteFavouriteQuoteDTOConverter);
        return modelMapper;
    }
}
