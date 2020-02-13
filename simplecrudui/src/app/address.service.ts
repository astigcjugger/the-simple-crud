import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  private baseUrl = 'http://localhost:8080/addresses';

  constructor(private http: HttpClient) { }

  getAddress(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createAddress(address: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, address);
  }

  updateAddress(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteAddress(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getAddressList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

}
