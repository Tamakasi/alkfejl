import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';import { LoginViewComponent } from '../../components/login-view/login-view.component';
import { FolderViewComponent } from '../../components/folder-view/folder-view.component';
import { TaskComponent } from '../../components/task/task.component';
import { NewtaskComponent } from '../../components/newtask/newtask.component';
import { NewfolderComponent } from '../../components/newfolder/newfolder.component';
import { RouteGuardService } from '../../services/route-guard.service';
import { AuthService } from '../../services/auth.service';

const appRoutes: Routes = [
  { path: '', canActivateChild: [RouteGuardService], children: [
    { path: '', component: LoginViewComponent },
    { path: 'login', component: LoginViewComponent },
    { path: 'folder', component: FolderViewComponent,  data: { roles: ['USER', 'ADMIN'] } },
    { path: 'folder/:id', component: TaskComponent,  data: { roles: ['USER', 'ADMIN'] } },
    { path: 'newtask', component: NewtaskComponent,  data: { roles: ['USER', 'ADMIN'] } },
    { path: 'newfolder', component: NewfolderComponent,  data: { roles: ['USER', 'ADMIN'] } },
  ]}
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [
    RouterModule
  ],
  declarations: [],
  providers: [RouteGuardService, AuthService]
})
export class AppRouterModule { }
