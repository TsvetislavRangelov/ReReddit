package sem3.its.ReReddit.business.services;

import sem3.its.ReReddit.persistence.entity.MessageEntity;

public interface SaveMessageUseCase {
    MessageEntity saveMessage(MessageEntity entity);
}
