ALTER TABLE announcements
ADD CONSTRAINT unique_announcement_guid UNIQUE (announcement_guid);