/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.green.teste.TesteBase;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 *
 * @author leandro.silva
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    "/AplicationContext.xml"
)
@WebAppConfiguration 
@Transactional
@TransactionConfiguration(transactionManager = "tmGreen", defaultRollback = false)
public abstract class AbstractTeste {
		
	
    

}
