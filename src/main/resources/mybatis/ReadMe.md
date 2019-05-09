任务自动完成触发器

create  trigger autoUpdateTask
before update on task
for each row
begin
	if new.task_submitted_num = new.task_target_num
	then set new.task_finished = 1 ;
	end if;
end;

drop trigger autoUpdateTask
