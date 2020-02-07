import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ErrorComponent } from './error/error.component';
import { HomeComponent } from './home/home.component';
import { MenuComponent } from './menu/menu.component';
import { FooterComponent } from './footer/footer.component';
import { AddCustomerComponent } from './add-customer/add-customer.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { UpdateCustomerComponent } from './update-customer/update-customer.component';

@NgModule({
  declarations: [
    AppComponent,
    ErrorComponent,
    HomeComponent,
    MenuComponent,
    FooterComponent,
    AddCustomerComponent,
    CustomerListComponent,
    CustomerDetailsComponent,
    UpdateCustomerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
