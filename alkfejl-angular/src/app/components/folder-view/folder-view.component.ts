import { Component, OnInit } from '@angular/core';
import { Task } from '../../classes/task';
import { Folder } from '../../classes/folder';
import { FolderService } from '../../services/folder.service';
import { TaskService } from '../../services/task.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-folder-view',
  templateUrl: './folder-view.component.html',
  styleUrls: ['./folder-view.component.css'],
  providers: [FolderService, TaskService, AuthService]
})
export class FolderViewComponent implements OnInit {
  

  private _folders: Folder[];
  private _task: Task[];
  
  constructor(
    private taskService: TaskService,
    private folderService: FolderService,
    private authService: AuthService,
  ) { 
    console.log("getfolders!!!!!!!!!!!const");
    this.folderService.getFolders().subscribe((folder: Folder[]) => {
      this._folders = folder;
    });
  }

  ngOnInit() {
  }

}

