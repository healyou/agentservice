----------- agent view -----------
CREATE VIEW agent_v
  AS
    SELECT
      agent.id,
      agent.mas_id,
      agent.name,
      agent.type_id,
      at.code as type_code,
      at.name as type_name,
      at.is_deleted as type_is_deleted,
      agent.create_date,
      agent.is_deleted
    FROM
      agent
  INNER JOIN agent_type as at ON agent.type_id = at.id;

----------- message view -----------
CREATE VIEW message_v
  AS
  SELECT
    message.id,
    message.sender_id,
    sender.mas_id as sender_mas_id,
    sender.name as sender_name,
    sender.type_id as sender_type_id,
    sender.type_code as sender_type_code,
    sender.type_name as sender_type_name,
    sender.type_is_deleted as sender_type_is_deleted,
    sender.create_date as sender_create_date,
    sender.is_deleted as sender_is_deleted,
    message.message_type_id,
    mt.code as message_type_code,
    mt.name as message_type_name,
    mt.message_order as message_type_message_order,
    mt.message_goal_type_id as message_type_message_goal_type_id,
    mtmgt.code as message_type_message_goal_type_code,
    mtmgt.name as message_type_message_goal_type_name,
    mtmgt.is_deleted as message_type_message_goal_type_is_deleted,
    mt.is_deleted as message_type_is_deleted,
    message.create_date,
    message.body_type_id,
    mbt.code as message_body_type_code,
    mbt.name as message_body_type_name,
    mbt.is_deleted as message_body_type_is_deleted,
    message.body
  FROM
    message
  INNER JOIN agent_v AS sender ON message.sender_id = sender.id
  INNER JOIN message_type as mt ON message.message_type_id = mt.id
  INNER JOIN message_goal_type as mtmgt ON mt.message_goal_type_id = mtmgt.id
  INNER JOIN message_body_type as mbt ON message.body_type_id = mbt.id;

----------- message type view -----------
CREATE VIEW message_type_v
AS
  SELECT
    message_type.id,
    message_type.code,
    message_type.name,
    message_type.message_order,
    message_type.message_goal_type_id,
    mgt.code as message_goal_type_code,
    mgt.name as message_goal_type_name,
    mgt.is_deleted as message_goal_type_is_deleted,
    message_type.is_deleted
  FROM
    message_type
    INNER JOIN message_goal_type as mgt ON message_type.message_goal_type_id = mgt.id;

----------- message recipient view -----------
CREATE VIEW message_recipient_v
  AS
    SELECT
      message_recipient.id,
      message_recipient.message_id,
      message_recipient.recipient_id,
      message_recipient.viewed_date,
      recipient.name as recipient_name,
      recipient.mas_id as recipient_mas_id,
      recipient.create_date as recipient_create_date,
      recipient.is_deleted as recipient_is_deleted,
      recipient.type_id as recipient_type_id,
      rt.code as recipient_type_code,
      rt.name as recipient_type_name,
      rt.is_deleted as recipient_type_is_deleted
    FROM
      message_recipient
      INNER JOIN agent as recipient ON message_recipient.recipient_id = recipient.id
      INNER JOIN agent_type as rt ON recipient.type_id = rt.id;