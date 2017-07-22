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
    sender.create_date as sender_create_date,
    sender.is_deleted as sender_is_deleted,
    sender.mas_id as sender_mas_id,
    sender.name as sender_name,
    sender.type_id as sender_type_id,
    sender.type_code as sender_type_code,
    sender.type_name as sender_type_name,
    sender.type_is_deleted as sender_type_is_deleted,
    sender.create_date as sender_create_date,
    sender.is_deleted as sender_is_deleted,
    message.communication_goal_id,
    cg.code as communication_goal_code,
    cg.name as communication_goal_name,
    cg.is_deleted as communication_goal_is_deleted,
    message.message_type_id,
    mt.code as message_type_code,
    mt.name as message_type_name,
    mt.message_order as message_type_message_order,
    mt.com_goal_id as message_type_com_goal_id,
    mt.is_deleted as message_type_is_deleted,
    message.create_date,
    message.viewed_date,
    message.is_viewed,
    message.body_type_id,
    mbt.code as message_body_type_code,
    mbt.name as message_body_type_name,
    mbt.is_deleted as message_body_type_is_deleted,
    message.body
  FROM
    message
  INNER JOIN agent_v AS sender ON message.sender_id = sender.id
  INNER JOIN communication_goal as cg ON message.communication_goal_id = cg.id
  INNER JOIN message_type as mt ON message.message_type_id = mt.id
  INNER JOIN message_body_type as mbt ON message.body_type_id = mbt.id;