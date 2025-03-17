import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DistanceCalculatorComponent } from './distance-calculator/distance-calculator.component';
import { PostcodeUpdaterComponent } from './postcode-updater/postcode-updater.component';
import { PostcodeListComponent } from './postcode-list/postcode-list.component';

@NgModule({
  declarations: [
    AppComponent,
    DistanceCalculatorComponent,
    PostcodeUpdaterComponent,
    PostcodeListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { } 