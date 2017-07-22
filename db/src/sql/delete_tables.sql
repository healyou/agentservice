DROP VIEW IF EXISTS agent_v;
DROP VIEW IF EXISTS message_v;

DROP TRIGGER IF EXISTS agent_v_insert_t;
DROP TRIGGER IF EXISTS agent_v_update_t;
DROP TRIGGER IF EXISTS agent_v_delete_t;
DROP TRIGGER IF EXISTS message_v_insert_t;
DROP TRIGGER IF EXISTS message_v_update_t;
DROP TRIGGER IF EXISTS message_v_delete_t;

drop table if exists agent;
drop table if exists agentType;
drop table if exists messageType;
drop table if exists communicationGoal;
drop table if exists messageBodyType;
drop table if exists message;
drop table if exists messageRecipient;