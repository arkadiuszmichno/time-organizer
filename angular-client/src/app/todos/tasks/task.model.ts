export class Task {
  public id: number;
  public name: string;
  public description: string;
  public endDate: string;

  constructor(name: string, descr?: string, date?: string) {
    this.name = name;
    this.description = descr;
    this.endDate = date;
  }
}
