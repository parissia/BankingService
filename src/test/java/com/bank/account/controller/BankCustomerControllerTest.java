package com.bank.account.controller;

import com.bank.account.constants.ResponseCodeDescription;
import com.bank.account.controller.BankCustomerController;
import com.bank.account.model.*;
import com.bank.account.service.AccountService;
import com.bank.account.service.CustomerService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(BankCustomerController.class)
class BankCustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private AccountService accountService;

    @Test
    public void testShowCustomerSuccess() throws Exception {
        when(accountService.getTotalBalance(1L)).thenReturn(200L);
        CustomerServiceResponse customerServiceResponse = new CustomerServiceResponse();
        Customer newCustomer = new Customer(1L, "Name", "Surname", 1000L);
        customerServiceResponse.setCustomer(newCustomer);
        customerServiceResponse.setServiceResponse(ResponseCodeDescription.SUCCESS_CUSTOMER);
        when(customerService.findCustomer(1L)).thenReturn(customerServiceResponse);

        mockMvc.perform(get("/api/v1/bank/customer/show")
                        .param("id", String.valueOf(1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("customer"));
    }

    @Test
    public void testShowCustomerError() throws Exception {
        CustomerServiceResponse customerServiceResponse = new CustomerServiceResponse();
        customerServiceResponse.setCustomer(null);
        customerServiceResponse.setServiceResponse(ResponseCodeDescription.ERROR_CUSTOMER);
        when(customerService.findCustomer(1L)).thenReturn(customerServiceResponse);
        mockMvc.perform(get("/api/v1/bank/customer/show")
                        .param("id", String.valueOf(1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }

    @Test
    public void testCreateNewBankAccountSuccess() throws Exception {
        AccountServiceResponse accountServiceResponse = new AccountServiceResponse();
        Account expectedAccount = new Account();
        expectedAccount.setCustomerId(1L);
        expectedAccount.setAccountId(1L);
        accountServiceResponse.setAccount(expectedAccount);
        accountServiceResponse.setServiceResponse(ResponseCodeDescription.SUCCESS_ACCOUNT);

        when(accountService.newAccount(1L)).thenReturn(accountServiceResponse);

        Customer inputCustomer = new Customer(1L, "test", "surTest", 1000L);
        MvcResult result = mockMvc.perform(post("/api/v1/bank/customer/account/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJson(inputCustomer)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        BankResponse expected = mapper.readValue(
                new ClassPathResource("SuccessResponse.json").getFile(),
                BankResponse.class);
        assertEquals(mapper.writeValueAsString(expected), result.getResponse().getContentAsString());

    }

    @Test
    public void testCreateNewBankAccountCustomerError() throws Exception {
        AccountServiceResponse accountServiceResponse = new AccountServiceResponse();
        Account expectedAccount = new Account();
        expectedAccount.setCustomerId(1L);
        expectedAccount.setAccountId(null);
        accountServiceResponse.setAccount(expectedAccount);
        accountServiceResponse.setServiceResponse(ResponseCodeDescription.ERROR_CUSTOMER);

        when(accountService.newAccount(1L)).thenReturn(accountServiceResponse);

        Customer inputCustomer = new Customer(1L, "test", "surTest", 100L);
        MvcResult result = mockMvc.perform(post("/api/v1/bank/customer/account/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJson(inputCustomer)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        BankResponse expected = mapper.readValue(
                new ClassPathResource("CustomerErrorResponse.json").getFile(),
                BankResponse.class);
        assertEquals(mapper.writeValueAsString(expected), result.getResponse().getContentAsString());

    }

    @Test
    public void testCreateNewBankAccountTransactionError() throws Exception {
        AccountServiceResponse accountServiceResponse = new AccountServiceResponse();
        Account expectedAccount = new Account();
        expectedAccount.setCustomerId(1L);
        expectedAccount.setAccountId(2L);
        accountServiceResponse.setAccount(expectedAccount);
        accountServiceResponse.setServiceResponse(ResponseCodeDescription.ERROR_TRANSACTION);

        when(accountService.newAccount(1L)).thenReturn(accountServiceResponse);

        Customer inputCustomer = new Customer(1L, "test", "surTest", 0L);
        MvcResult result = mockMvc.perform(post("/api/v1/bank/customer/account/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJson(inputCustomer)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        BankResponse expected = mapper.readValue(
                new ClassPathResource("TransactionErrorResponse.json").getFile(),
                BankResponse.class);
        assertEquals(mapper.writeValueAsString(expected), result.getResponse().getContentAsString());

    }

    private String convertObjectToJson(Object request) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(request);
    }
}
