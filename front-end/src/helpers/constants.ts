export const baseURL = '/api/';
import productPackages from '../config/productPackages.json';

export const appName = 'Stock Administrator';

export const viewProductsTitle = 'View Products';
export const addProductTitle = 'Add Product';
export const editProductTitle = 'Edit Product';

export const viewStocksTitle = 'Search Stocks';
export const addStockTitle = 'Add Stock';
export const addInvoiceTitle = 'Add Invoice';
export const addSalesTitle = 'Add Sales';

export const stockIdLabel = 'Stock ID';
export const stockDateLabel = 'Date';
export const incomingStockCountLabel = 'Incoming Stocks';
export const outgoingStockCountLabel = 'Outgoing Stocks';
export const totalStockCountLabel = 'Total Stocks';
export const ManageStocksLabel = 'Recent Stocks';


export const fromDateLabel = 'From Date';
export const toDateLabel = 'To Date';
export const exportLabel = 'Export';

export const productCodeLabel = 'Product Code';
export const productNameLabel = 'Product Name';
export const productPackagingLabel = 'Packaging';
export const manageProductsLabel = 'Manage Products';
export const productIdLabel = 'Product ID';
export const actionsLabel = 'Actions';
export const searchLabel = 'Search';
export const saveLabel = 'Save';
export const clearLabel = 'Clear';

export const userNameLabel = 'User Name';
export const passwordLabel = 'Password';
export const shopLabel = 'Shop';
export const loginLabel = 'Login';

export const productCodePlaceHolder = 'Enter the product code here';
export const productNamePlaceHolder = 'Enter the product name here';
export const productPackagingPlaceHolder = 'Select a product packaging';
export const usernamePlaceHolder = 'Enter a user name';
export const passwordPlaceHolder = 'Enter a password';
export const shopPlaceHolder = 'Select a shop';

export const incomingStockCountPlaceHolder = 'Enter the Incoming Stock Count';
export const outgoingStockCountPlaceHolder = 'Enter the Outgoing Stock Count';

export const productsMenu = 'Products';
export const stocksMenu = 'Stocks';
export const logoutMenu = 'Logout';
export const aboutMenu = 'About';

export const errorOccurred = 'Error Occurred';
export const fetchingProduct = 'Fetching products...';
export const creatingProduct = 'Creating product...';
export const updatingProduct = 'Updating product...';
export const deletingProduct = 'Deleting product...';
export const authenticating = 'Authenticating...';
export const loggingOut = 'Logging out...';
export const fetchingShops = 'Fetching shops...';

export const fetchingStocks = 'Fetching Stocks...';
export const creatingStocks = 'Creating Stocks...';
export const searchingStocks = 'Searching Stocks...';
export const stockAdded = 'Successfully Added';

export const stockDownloaded = 'Stocks Exported Successfully.';
export const noStockFound = 'No Stocks Found for the selected Date range';
export const invalidShopCode = 'Invalid Shop code';
export const somethingWentWrong = 'Something Went Wrong. Please contact the Admin';
export const invalidDateRange = 'From Date should be smaller than To Date';
  
export type LocalPackaging = { name: string };
export const packaging: LocalPackaging[] = productPackages;
