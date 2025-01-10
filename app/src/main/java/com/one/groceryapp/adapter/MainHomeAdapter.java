//package com.one.groceryapp.adapter;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.one.groceryapp.R;
//import com.one.groceryapp.model.CategoriesModel;
//import com.one.groceryapp.model.FeatureProductModel;
//import com.one.groceryapp.model.MainHomeModel;
//import com.one.groceryapp.model.TitleModel;
//
//import java.util.List;
//
//public class MainHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    public static final int TITLE = 0;
//    public static final int CATEGORY_TYPE = 1;
//    public static final int FEATURE_PRODUCT_TYPE = 2;
//    List<MainHomeModel> mainHomeModelList;
//    RecyclerView rcv;
//
//    Context context;
//
//    public MainHomeAdapter(List<MainHomeModel> mainHomeModelList, Context context) {
//        this.mainHomeModelList = mainHomeModelList;
//        this.context = context;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return mainHomeModelList.get(position).getViewType();
////        if (position == 0) {
////            return TITLE;
////        } else if (position == 1) {
////            return CATEGORY_TYPE;
////        } else {
////            return FEATURE_PRODUCT_TYPE;
////        }
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        View view = null;
//        RecyclerView.ViewHolder viewHolder = null;
//        rcv = view.findViewById(R.id.rcv);
//        if (viewType == TITLE) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_title, parent, false);
//            viewHolder = new Title(view);
//        } else if (viewType == CATEGORY_TYPE) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_category, parent, false);
//            viewHolder = new CategoryViewHolder(view);
//        } else if (viewType == FEATURE_PRODUCT_TYPE) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_product, parent, false);
//            viewHolder = new FeatureProduct(view);
//        }
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        MainHomeModel item = mainHomeModelList.get(position);
//        switch (item.getViewType()) {
//            case TITLE:
//                TitleModel title = (TitleModel) item.getData();
//                ((Title) holder).title.setText(title.getTitle());
//                break;
//
//            case CATEGORY_TYPE:
//                CategoriesModel category = (CategoriesModel) item.getData();
//                CategoryViewHolder categoryHolder = (CategoryViewHolder) holder;
//                categoryHolder.category_image.setImageResource(category.getCategoryimage());
//                categoryHolder.category_title.setText(category.getCategoryname());
//                LinearLayoutManager linearLayoutManager =new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
//                rcv.setLayoutManager(linearLayoutManager);
//                break;
//
//            case FEATURE_PRODUCT_TYPE:
//                FeatureProductModel product = (FeatureProductModel) item.getData();
//                FeatureProduct productHolder = (FeatureProduct) holder;
//                productHolder.product_image.setImageResource(product.getImageProduct());
//                productHolder.product_name.setText(product.getProductName());
//                productHolder.product_quantity.setText(product.getQuantity());
//                productHolder.price.setText(product.getPrice());
//                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
//                rcv.setLayoutManager(gridLayoutManager);
//                break;
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return mainHomeModelList.size();
//    }
//
//    static class CategoryViewHolder extends RecyclerView.ViewHolder {
//        TextView category_title;
//        ImageView category_image;
//        @SuppressLint("WrongViewCast")
//        public CategoryViewHolder(@NonNull View itemView) {
//            super(itemView);
//            category_title = itemView.findViewById(R.id.category_text);
//            category_image = itemView.findViewById(R.id.category_image);
//        }
//    }
//
//    static class FeatureProduct extends RecyclerView.ViewHolder {
//        ImageView product_image;
//        TextView product_name;
//        TextView product_quantity;
//        TextView price;
//
//        public FeatureProduct(@NonNull View itemView) {
//            super(itemView);
//            product_image = itemView.findViewById(R.id.product_image);
//            product_name = itemView.findViewById(R.id.product_name);
//            product_quantity = itemView.findViewById(R.id.product_quantity);
//            price = itemView.findViewById(R.id.price);
//        }
//    }
//
//    static class Title extends RecyclerView.ViewHolder {
//        TextView title;
//
//        public Title(@NonNull View itemView) {
//            super(itemView);
//            title = itemView.findViewById(R.id.title);
//        }
//    }
//}
