export class Folder {
    public id: number;

    public constructor(
        private name: string,        
        private color: string,
        private description: string
    ) {}

    public getId(): number {
        return this.id;
    }


}


