import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Task } from '../../classes/task';
import { TaskService } from '../../services/task.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css'],
  providers: [TaskService]
})
export class TaskComponent implements OnInit {
  @Input()
  public task: Task;

  constructor(
    private taskService: TaskService
  ) { 
    
  }

  ngOnInit() {
    this._task = this.task;
    this.deadline = new Date(this._task.deadline);
    this.deadline = this.deadline.getFullYear() + "-" +  this.deadline.getMonth() + "-"+ this.deadline.getDay()+
      " "+  this.deadline.toTimeString().split(" ")[0];

    this.changeColor();
  }
  private deadline;
  private _task: Task;
  private stateColor;
  private editOn = false;

  public done() {
    this.taskService.complete(this._task.id, "completed").subscribe((data)=> {
      this._task.state = "completed";
      this.changeColor();
    });
  }

  public editDone(message: string) {
    this.taskService.complete(this._task.id, message).subscribe((data)=> {
      console.log(this._task);
      this._task.state = message;
      this.editOn = false;
      this.changeColor();
    });
  }

  public isEditOn() {
    return this.editOn;
  }

  public edit() {
    this.editOn = !this.editOn;
    console.log(this.editOn);
  }

  private changeColor() {
    switch(this._task.state) {
      case 'created':
        this.stateColor = "#bbdefb";
        break;
      case 'completed':
        this.stateColor = "#c8e6c9";
        break;
      case 'dropped':
        this.stateColor = "#f5f5f5";
        break;
    }
  }
}
