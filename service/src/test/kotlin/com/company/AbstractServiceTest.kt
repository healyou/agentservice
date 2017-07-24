package com.company

import org.junit.Ignore
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional

/**
 * @author Nikita Gorodilov
 */

@Ignore
@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration("classpath:beans.xml")
@Transactional("transactionManager")
abstract class AbstractServiceTest:  AbstractTransactionalJUnit4SpringContextTests()
