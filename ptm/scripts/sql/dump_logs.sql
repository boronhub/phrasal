SELECT username,url,code,interface,[order],create_time,start_time,end_time,complete,training,valid,text,log from tmapp_translationsession, tmapp_sourcedocument, tmapp_language, auth_user where tmapp_translationsession.user_id=auth_user.id and tmapp_translationsession.src_document_id=tmapp_sourcedocument.id and tmapp_translationsession.tgt_language_id=tmapp_language.id;