package com.company.objects;

import com.company.CreateTypesDao;

/**
 * Создание и хранение тестовых типов данных
 *
 * @author Nikita Gorodilov
 */
public class TypesObjects {

    /* Коды для типов агентов */
    public static final String testAgentCode1 = CreateTypesDao.Companion.getTestAgentCode1();
    public static final String testAgentCode2 = CreateTypesDao.Companion.getTestAgentCode2();
    /* Коды для типов тел сообщений */
    public static final String testMessageBodyCode1 = CreateTypesDao.Companion.getTestMessageBodyCode1();
    /* Коды для типов целей общения сообщений */
    public static final String testMessageGoalCode1 = CreateTypesDao.Companion.getTestMessageGoalCode1();
    /* Коды для типов сообщений */
    public static final String testMessageCode1 = CreateTypesDao.Companion.getTestMessageCode1();
    public static final String testMessageCode2 = CreateTypesDao.Companion.getTestMessageCode2();
}
