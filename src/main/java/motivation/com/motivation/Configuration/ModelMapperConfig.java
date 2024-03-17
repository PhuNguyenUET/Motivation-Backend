package motivation.com.motivation.Configuration;

import motivation.com.motivation.DTO.FavouriteQuoteDTO;
import motivation.com.motivation.Model.Quote;
import motivation.com.motivation.Model.UserQuote;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.ModelMap;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapperBean() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<Quote, FavouriteQuoteDTO> quoteDTOConverter = new Converter<Quote, FavouriteQuoteDTO>() {
            @Override
            public FavouriteQuoteDTO convert(MappingContext<Quote, FavouriteQuoteDTO> context) {
                Quote quote = context.getSource();
                FavouriteQuoteDTO favouriteQuoteDTO = context.getDestination();
                favouriteQuoteDTO.setQuote(quote.getQuote());
                favouriteQuoteDTO.setAuthor(quote.getAuthor());
                favouriteQuoteDTO.setUserCreated(false);
                return favouriteQuoteDTO;
            }
        };
        modelMapper.addConverter(quoteDTOConverter);
        Converter<UserQuote, FavouriteQuoteDTO> userQuoteFavouriteQuoteDTOConverter = new Converter<UserQuote, FavouriteQuoteDTO>() {
            @Override
            public FavouriteQuoteDTO convert(MappingContext<UserQuote, FavouriteQuoteDTO> context) {
                UserQuote quote = context.getSource();
                FavouriteQuoteDTO favouriteQuoteDTO = context.getDestination();
                favouriteQuoteDTO.setQuote(quote.getQuote());
                favouriteQuoteDTO.setAuthor(quote.getAuthor());
                favouriteQuoteDTO.setUserCreated(true);
                return favouriteQuoteDTO;
            }
        };
        modelMapper.addConverter(userQuoteFavouriteQuoteDTOConverter);
        return modelMapper;
    }
}
