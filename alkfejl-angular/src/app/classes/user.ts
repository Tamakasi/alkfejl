import { Folder } from "./folder";

export class User {
    constructor (
        public id: number,
        public name: string,
        public email: string,
        public role: string,
        public folders: Folder[] = null
    ) {}
}
