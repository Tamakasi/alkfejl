import { User } from './user';
import { Folder } from './folder';

export class Task {
    public id: number;

    public constructor(
        public deadline: number,        
        public state: string,
        private description: string,
        private priority: number,
        //private user: User = null,
        //private folder: Folder = null
    ) {}

    public getId(): number {
        return this.id;
    }

    

    /*
    public getUser(): User {
        return this.user;
    }*/
    /*
    public getFolder(): Folder {
        return this.folder;
    }*/



}


