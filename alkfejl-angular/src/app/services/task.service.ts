import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Task } from '../classes/task';
import { Folder } from '../classes/folder';
import { api } from '../config/api';

@Injectable()
export class TaskService {

  constructor(
    private httpClient: HttpClient
  ) { }


  public listByFolder(folderId: number): Observable<Task[]> {
    return this.httpClient.get(api + 'task/listbyfolder?id='+ folderId);
  }

  public addTask(folder: Folder, deadline:string, description: string, priority: number): Observable<any> {
    return this.httpClient.post(api + 'task/add', {folder, deadline, description, priority});
  }

  public complete(id: number, message:string): Observable<Task> {
    return this.httpClient.post(api + 'task/complete', {id, message});
  }

  public listTasks(): Observable<Task[]> {
    return this.httpClient.get(api + 'folder' + "/list");
  }
}
