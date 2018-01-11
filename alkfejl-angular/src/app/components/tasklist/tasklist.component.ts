import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { Task } from '../../classes/Task';
import { Folder } from '../../classes/Folder';
import { TaskService } from '../../services/task.service';

@Component({
  selector: 'app-tasklist',
  templateUrl: './tasklist.component.html',
  styleUrls: ['./tasklist.component.css'],
  providers: [TaskService]
})
export class TasklistComponent implements OnInit {
  @Input()
  public tasks: Task[];
  @Input()
  public folder: Folder;
  
  
  constructor(
    private taskService: TaskService,
  ) { }

  ngOnInit() {
    this.taskService.listByFolder(this.folder.id).subscribe((task: Task[]) => {
      this.tasks = task;
    });
  }

}
