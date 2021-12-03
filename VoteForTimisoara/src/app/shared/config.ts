export const baseUrl = 'https://localhost:3000';

// tslint:disable-next-line:class-name
export class config {
  static loginUrl = baseUrl + '/app/hello/';
}

export enum errorResults {
  REQUEST_TIMEOUT = 'Request timeout',
}
