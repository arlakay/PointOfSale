package com.ingenico.PointOfSale;

abstract interface CommonActivityInterface 
{
	abstract void onBarCodeReceived(String barCodeValue, int symbology);
	abstract void onBarCodeClosed();
	abstract void onStateChanged(String state);
}
