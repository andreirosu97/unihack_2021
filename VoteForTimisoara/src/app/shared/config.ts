export const baseUrl = 'https://app-unihack.herokuapp.com';

// tslint:disable-next-line:class-name
export class config {
  static loginUrl = baseUrl + '/app/hello/';
  static registerUrl = baseUrl + '/register/user';
}

export enum errorResults {
  REQUEST_TIMEOUT = 'Request timeout',
}
