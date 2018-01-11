import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { UiModule } from './modules/ui/ui.module';
import { AppRouterModule } from './modules/app-router/app-router.module';
import { HttpClientModule } from '@angular/common/http'; 
import { AppComponent } from './app.component';
import { LoginViewComponent } from './components/login-view/login-view.component';
import { FolderViewComponent } from './components/folder-view/folder-view.component';
import { TasklistComponent } from './components/tasklist/tasklist.component';
import { TaskComponent } from './components/task/task.component';
import { NewtaskComponent } from './components/newtask/newtask.component';
import { NewfolderComponent } from './components/newfolder/newfolder.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginViewComponent,
    FolderViewComponent,
    TasklistComponent,
    TaskComponent,
    NewtaskComponent,
    NewfolderComponent,
  ],
  imports: [
    BrowserModule, 
    UiModule,
    AppRouterModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
