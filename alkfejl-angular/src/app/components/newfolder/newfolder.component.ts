import { Component, OnInit } from '@angular/core';
import { FolderService } from '../../services/folder.service';

@Component({
  selector: 'app-newfolder',
  templateUrl: './newfolder.component.html',
  styleUrls: ['./newfolder.component.css'],
  providers: [FolderService]
})
export class NewfolderComponent implements OnInit {

  constructor(
    private folderService: FolderService,
  ) { }

  private error = null;
  ngOnInit() {
  }

  private submit(name: string, description: string, color: string) {
    if( name == "" || description == "" || color == "") {
      this.error = "Tölts ki minden mezőt!";
    } else {
      this.folderService.addFolder(name, description, color).subscribe((data)=>console.log(data));
    }
  }

}
