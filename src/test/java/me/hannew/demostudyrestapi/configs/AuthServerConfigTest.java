package me.hannew.demostudyrestapi.configs;

import me.hannew.demostudyrestapi.accounts.Account;
import me.hannew.demostudyrestapi.accounts.AccountRole;
import me.hannew.demostudyrestapi.accounts.AccountService;
import me.hannew.demostudyrestapi.common.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthServerConfigTest extends BaseTest {

    @Autowired
    AccountService accountService;

    @Test
    public void getAuthToken() throws Exception{
        Account hannew =  Account.builder()
                .email("hannew@email.com")
                .password("hannew")
                .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                .build();

        this.accountService.saveAccount(hannew);
        String clientId = "myApp";
        String clientSecret = "pass";

        this.mockMvc.perform(post("/oauth/token")
                    .with(httpBasic(clientId, clientSecret))
                    .param("")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists());

    }
}
