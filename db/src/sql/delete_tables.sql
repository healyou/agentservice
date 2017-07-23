DROP VIEW IF EXISTS agent_v;
DROP VIEW IF EXISTS message_v;

DROP TRIGGER IF EXISTS agent_v_insert_t;
DROP TRIGGER IF EXISTS agent_v_update_t;
DROP TRIGGER IF EXISTS agent_v_delete_t;
DROP TRIGGER IF EXISTS message_v_insert_t;
DROP TRIGGER IF EXISTS message_v_update_t;
DROP TRIGGER IF EXISTS message_v_delete_t;

drop table if exists agent;
drop table if exists agent_type;
drop table if exists message_type;
drop table if exists communication_goal;
drop table if exists message_body_type;
drop table if exists message;
drop table if exists message_recipient;