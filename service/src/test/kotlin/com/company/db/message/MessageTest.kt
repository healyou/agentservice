package com.company.db.message

import com.company.db.core.agent.Agent
import com.company.db.core.agent.AgentType
import com.company.db.core.message.Message
import com.company.db.core.message.MessageBodyType
import com.company.db.core.message.MessageGoalType
import com.company.db.core.message.MessageType
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*

/**
 * Тестирование функциональности класса Message
 *      Здесь нет привязки классов к базе данных
 *      Проверяется только работа самого класса
 *
 * @author Nikita Gorodilov
 */
class MessageTest : Assert() {

    private lateinit var message: Message

    private val id = 1L
    private val sender = createNextTestAgent()
    private val recipients = createRecipients()
    private val goalType = createMessageGoalType()
    private val type = createMessageType()
    private val createDate = Date(System.currentTimeMillis())
    private var viewedDate: Date? = null
    private val isViewed = false
    private val bodyType = createJsonMessageBodyType()
    private val body = "{}"

    @Before
    fun setup() {
        message = Message(
                id,
                sender,
                recipients,
                goalType,
                type,
                createDate,
                viewedDate,
                isViewed,
                bodyType,
                body
        )
    }

    @Test
    fun testTypeGetData() {
        assertEquals(id, message.id)
        assertEquals(sender, message.sender)
        assertEquals(recipients, message.recipients)
        assertEquals(goalType, message.goalType)
        assertEquals(type, message.type)
        assertEquals(createDate, message.createDate)
        assertEquals(viewedDate, message.viewedDate)
        assertEquals(isViewed, message.isViewed)
        assertEquals(bodyType, message.bodyType)
        assertEquals(body, message.body)
    }

    @Test
    fun testTypeSetDate() {
        /* новые значение */
        val newId = 2L
        val newSender = createNextTestAgent()
        val newRecipients = createRecipients()
        val newGoalType = createMessageGoalType()
        val newType = createMessageType()
        val newCreateDate = Date(System.currentTimeMillis())
        val newViewedDate = Date(System.currentTimeMillis())
        val newIsViewed = true
        val newBodyType = createJsonMessageBodyType()
        val newBody = "{gg: 1}"

        message.id = newId
        message.sender = newSender
        message.recipients = newRecipients
        message.goalType = newGoalType
        message.type = newType
        message.createDate = newCreateDate
        message.viewedDate = newViewedDate
        message.isViewed = newIsViewed
        message.bodyType = newBodyType
        message.body = newBody

        assertEquals(newId, message.id!!)
        assertEquals(newSender, message.sender)
        assertEquals(newRecipients, message.recipients)
        assertEquals(newGoalType, message.goalType)
        assertEquals(newType, message.type)
        assertEquals(newCreateDate, message.createDate)
        assertEquals(newViewedDate, message.viewedDate)
        assertEquals(newIsViewed, message.isViewed)
        assertEquals(newBodyType, message.bodyType)
        assertEquals(newBody, message.body)
    }

    companion object {
        /* id для следующего агента */
        private var _id = 1L
        private var nextId: Long = _id
            get() = _id++

        /* Создание тестового агента с новый id */
        private fun createNextTestAgent(): Agent {
            return Agent(
                    nextId,
                    "$nextId",
                    "$nextId",
                    AgentType(
                            nextId,
                            AgentType.Code.WORKER,
                            "agentTypeName",
                            false
                    ),
                    Date(System.currentTimeMillis()),
                    false
            )
        }

        /* Создание экземпляра MessageGoalType с новый id */
        private fun createMessageGoalType(): MessageGoalType {
            return MessageGoalType(
                    nextId,
                    MessageGoalType.Code.TASK_DECISION,
                    "messageGoalTypeName",
                    false
            )
        }

        /* Создание экземпляра MessageType с новый id */
        private fun createMessageType(): MessageType {
            return MessageType(
                    nextId,
                    MessageType.Code.TASK_SOLUTION_ANSWER,
                    "messageTypeName",
                    3L,
                    createMessageGoalType(),
                    false
            )
        }

        /* Создание экземпляра MessageBodyType с новый id */
        private fun createJsonMessageBodyType(): MessageBodyType {
            return MessageBodyType(
                    nextId,
                    MessageBodyType.Code.JSON,
                    "messageBodyTypeName",
                    false
            )
        }

        /* Создание множества агентов, которым адресовано сообщение */
        private fun createRecipients(): List<Agent> {
            return arrayListOf<Agent>(
                    createNextTestAgent(),
                    createNextTestAgent(),
                    createNextTestAgent()
            )
        }
    }
}