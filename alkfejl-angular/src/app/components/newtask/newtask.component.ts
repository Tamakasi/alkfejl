import { Component, OnInit } from '@angular/core';
import { Folder } from '../../classes/folder';
import { Task } from '../../classes/task';
import { FolderService } from '../../services/folder.service';
import { TaskService } from '../../services/task.service';

@Component({
  selector: 'app-newtask',
  templateUrl: './newtask.component.html',
  styleUrls: ['./newtask.component.css'],
  providers: [FolderService, TaskService]
})
export class NewtaskComponent implements OnInit {

  constructor(
    private folderService: FolderService,
    private TaskService: TaskService,
  ) { }

  ngOnInit() {
    this.folderService.getFolders().subscribe((folders: Folder[])=>{
      this._folders = folders;
      this.selectedFolder = this._folders[0];
    });
  }

  private _folders: Folder[];
  private selectedFolder;
  private error= null;

  private submit(deadline: string, description: string, priority: number) {
    if( deadline == "" || description == "" || priority == null || this.selectedFolder == null) {
      this.error = "Tölts ki minden mezőt!";
    } else if( priority > 10 || priority < 0) {
      this.error = "Prioritásnak 0 és 10 között kell lennie!"
    } else {
      this.error=null;
      this.TaskService.addTask(this.selectedFolder, deadline, description, priority).subscribe((data)=>console.log(data));
    }
  }

}
