-- Уведомления 
CREATE TABLE notifications (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,                  -- Заголовок
    message TEXT NOT NULL,                        -- Текст сообщения  
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING', -- PENDING, SENT, FAILED
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    sent_at TIMESTAMP                             -- Когда отправлено
);

-- Индексы
CREATE INDEX idx_notifications_status ON notifications(status);      
CREATE INDEX idx_notifications_created ON notifications(created_at); 